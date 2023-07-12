#include "Swiat.h"
#include "Organizm.h"
#include <iostream>
#include <time.h>

#include "WilczeJagody.h"
#include "Barszcz.h"
#include "Antylopa.h"
#include "Lis.h"
#include "Trawa.h"
#include "Guarana.h"
#include "Czlowiek.h"
#include "Owca.h"
#include "Mlecz.h"
#include "Zolw.h"
#include "Wilk.h"

#include "Defines.h"

#include <fstream>

Swiat::Swiat(int x, int y)
{
	this->rozmiarSwiata.x = x;
	this->rozmiarSwiata.y = y;
	this->tura = 0;
	this->Organizmy = new Organizm * [x * y];
	for (int i = 0; i < x * y; i++)
	{
		this->Organizmy[i] = NULL;
	}
}

Swiat::~Swiat()
{
	delete[] Organizmy;
}

void Swiat::start()
{
	int liczba;
	cout << "1. Nowa gra \n2. Wczytaj" << endl;
	cin >> liczba;
	if (liczba == 1) this->stworzSwiat();
	if (liczba == 2) this->wczytaj();
	if (liczba > 2)
	{
		exit(-1);
	}
	while (true) this->nowaTura();
}

void Swiat::stworzSwiat()
{
	this->czlowiekWsk = (Czlowiek*)this->nowyOrganizm(0, 0, 'C');
	int x, y;
	for (int i = 0; i < START_OBJ_NUMBER; i++)
	{
		x = rand() % rozmiarSwiata.x;
		y = rand() % rozmiarSwiata.y;
		typOrganizmu table[] = { ANTYLOPA, WILCZEJAGODY, BARSZCZ, LIS, TRAWA, GUARANA, OWCA, MLECZ, ZOLW, WILK };
		typOrganizmu random = table[rand() % NUMBER_OF_DIFFRENT_ORGANISMS];
		if (this->Organizmy[x * this->rozmiarSwiata.y + y] == NULL)
			this->nowyOrganizm(x, y, random);
	}
	this->rysujSwiat();
}

void Swiat::nowaTura()
{
	this->tura++;
	cout << "\nTurn " << tura << endl;
	for (int inicjatywa = MAX_INIT - 1; inicjatywa >= 0; inicjatywa--)
	{
		for (int ageReverse = tablicaInicjatywy[inicjatywa].size() - 1; ageReverse >= 0; ageReverse--)
		{
			//cout << this->tablicaInicjatywy[inicjatywa][ageReverse]->getWiek() << " " << tablicaInicjatywy[inicjatywa][ageReverse]->getZnak() << " " << tablicaInicjatywy[inicjatywa][ageReverse]->getInicjatywa() << endl;
			this->tablicaInicjatywy[inicjatywa][ageReverse]->wykonajRuch();
		}
		for (int ageReverse = tablicaInicjatywy[inicjatywa].size() - 1; ageReverse >= 0; ageReverse--) {
			this->tablicaInicjatywy[inicjatywa][ageReverse]->starzejSie();
			this->tablicaInicjatywy[inicjatywa][ageReverse]->setRozmnozylSie(false);
		}
	}
	//getchar();
	this->rysujSwiat();
}

void Swiat::rysujSwiat()
{
	system("cls");
	cout << "Czlowiek sila: " << this->czlowiekWsk->getSila() << "\n";
	for (int i = 0; i <= this->rozmiarSwiata.y + 1; i++)cout << "#";
	cout << "\n#";
	for (int x = 0; x < this->rozmiarSwiata.x; x++)
	{
		for (int y = 0; y < this->rozmiarSwiata.y; y++)
		{
			if (this->Organizmy[x * this->rozmiarSwiata.y + y] == NULL)
			{
				cout << " ";
			}
			else
			{
				cout << this->Organizmy[x * this->rozmiarSwiata.y + y]->getZnak();
			}
		}
		cout << "#\n#";
	}
	for (int i = 0; i < this->rozmiarSwiata.y + 1; i++)cout << "#";
	this->dziennik.aktualizujDziennik();
}

void Swiat::zarzadzanieRuchem(int fromX, int fromY, int toX, int toY)
{
	int from = fromX * this->rozmiarSwiata.y + fromY, to = toX * this->rozmiarSwiata.y + toY;
	if (this->Organizmy[from] == czlowiekWsk)
		this->dziennik.czlowiekSuperMoc(czlowiekWsk->getSuperMocCount());
	if (this->Organizmy[to] == NULL)
	{
		this->zamienMiejscami(fromX, fromY, toX, toY);
		return;
	};
	if (this->Organizmy[to]->getZnak() == this->Organizmy[from]->getZnak() && this->Organizmy[to] != this->Organizmy[from])			//rozmnazanie
	{
		if (!this->Organizmy[to]->getRozmnozylSie() && !this->Organizmy[from]->getRozmnozylSie() && this->Organizmy[from]->rozmnoz(toX, toY)) {
			this->Organizmy[from]->setRozmnozylSie(true); this->Organizmy[to]->setRozmnozylSie(true);
			this->dziennik.opisRozmnazania(this->Organizmy[from]);
		}
	}
	else
	{
		if (!this->Organizmy[from]->kolizja(this->Organizmy[to]))return;
		if (!this->Organizmy[to]->kolizja(this->Organizmy[from]))return;
		//walka
		if (this->Organizmy[from]->getSila() >= this->Organizmy[to]->getSila())
		{
			this->dziennik.opisWalki(this->Organizmy[from], this->Organizmy[to]);
			this->usunOrganizm(*(this->Organizmy[to]));
		}
		else
		{
			this->dziennik.opisWalki(this->Organizmy[to], this->Organizmy[from]);
			this->usunOrganizm(*(this->Organizmy[from]));
		}
		this->zamienMiejscami(fromX, fromY, toX, toY);
	};
}

void Swiat::usunOrganizm(Organizm& organizm)
{
	int inicjatywa = organizm.getInicjatywa();
	for (vector <Organizm*>::iterator i = this->tablicaInicjatywy[inicjatywa].begin(); i < this->tablicaInicjatywy[inicjatywa].end(); i++)
	{
		if (*i == &organizm)
		{
			this->tablicaInicjatywy[inicjatywa].erase(i);
			break;
		}
	}
	this->Organizmy[organizm.getX() * this->rozmiarSwiata.y + organizm.getY()] = NULL;
	delete& organizm;
}

void Swiat::barszczSmierci(int ktory) {
	if (((this->Organizmy[ktory])->getZnak()) != 'b')
		this->usunOrganizm(*(this->Organizmy[ktory]));
}

bool Swiat::pustePole(int x, int y)
{
	if (!this->wGranicach(x, y))return false;
	if (this->Organizmy[x * this->rozmiarSwiata.y + y] == NULL)return true;
	return false;
}

bool Swiat::wGranicach(int x, int y)
{
	if (x < this->rozmiarSwiata.x && x >= 0 && y >= 0 && y < this->rozmiarSwiata.y)
	{
		return true;
	}
	return false;
}

int Swiat::getX() {
	return this->rozmiarSwiata.x;
}

int Swiat::getY() {
	return this->rozmiarSwiata.y;
}

void Swiat::zamienMiejscami(int ad1X, int ad1Y, int ad2X, int ad2Y)
{
	int pierwszy = ad1X * this->rozmiarSwiata.y + ad1Y,
		drugi = ad2X * this->rozmiarSwiata.y + ad2Y;

	if (this->Organizmy[pierwszy] != NULL)
	{
		this->Organizmy[pierwszy]->setX(ad2X);
		this->Organizmy[pierwszy]->setY(ad2Y);
	}
	if (this->Organizmy[drugi] != NULL)
	{
		this->Organizmy[drugi]->setY(ad1Y);
		this->Organizmy[drugi]->setX(ad1X);
	}
	swap(this->Organizmy[pierwszy], this->Organizmy[drugi]);
	return;
}

void Swiat::zapisz()
{
	fstream plik;
	cout << "Podan nazwe pliku:" << endl;
	string nazwa;
	cin >> nazwa;
	plik.open(nazwa, ios::out | ios::trunc);
	plik << this->tura << endl;
	plik << this->rozmiarSwiata.x << " " << this->rozmiarSwiata.y << endl;
	for (int i = 0; i < this->rozmiarSwiata.x * this->rozmiarSwiata.y; i++)
	{
		if (this->Organizmy[i] != NULL)
		{
			plik << this->Organizmy[i]->getZnak() << " ";
			plik << this->Organizmy[i]->getWiek() << " ";
			plik << this->Organizmy[i]->getSila() << " ";
			plik << this->Organizmy[i]->getInicjatywa() << " ";
			plik << this->Organizmy[i]->getX() << " ";
			plik << this->Organizmy[i]->getY() << " ";
			if (this->Organizmy[i] == this->czlowiekWsk)plik << this->czlowiekWsk->getSuperMocCount() << " " << this->czlowiekWsk->getSuperMocCd() << " ";
			plik << endl;
		}
	}
	plik.close();
}

void Swiat::wczytaj()
{
	fstream plik;
	cout << "Podaj nazwe pliku" << endl;
	string nazwa;
	cin >> nazwa;
	plik.open(nazwa, ios::in);
	if (!plik.good())return;
	plik >> this->tura;
	plik >> this->rozmiarSwiata.x;
	plik >> this->rozmiarSwiata.y;
	char znak;
	int wiek, sila, inicjatywa, Posx, Posy, cd, count;
	while (true) {
		if (!(plik >> znak))
			break;
		plik >> wiek >> sila >> inicjatywa >> Posx >> Posy;
		if (znak != 'C')
		{
			Organizm* ptr = NULL;
			ptr = this->nowyOrganizm(Posx, Posy, znak);
			ptr->setInicjatywaWiek(inicjatywa, wiek);
			ptr->setSila(sila);
		}
		else
		{
			plik >> count >> cd;
			this->czlowiekWsk = (Czlowiek*)this->nowyOrganizm(Posx, Posy, 'C');
			this->czlowiekWsk->setInicjatywaWiek(inicjatywa, wiek);
			this->czlowiekWsk->setSila(sila);
			this->czlowiekWsk->setSuperMocCd(cd);
			this->czlowiekWsk->setSuperMocCount(count);
		}
	}
	plik.close();
	this->rysujSwiat();
}

void Swiat::aktualizujTabliceInicjatywy(Organizm* pierwszy, int drugiInicjatywa, int drugiWiek)
{
	if (this->tablicaInicjatywy[pierwszy->getInicjatywa()].size() == 1)
	{
		this->tablicaInicjatywy[pierwszy->getInicjatywa()].clear();
	}
	else
	{
		for (vector <Organizm*>::iterator iter = this->tablicaInicjatywy[pierwszy->getInicjatywa()].begin(); iter < this->tablicaInicjatywy[pierwszy->getInicjatywa()].end(); iter++)
		{
			if (*iter == pierwszy)
			{
				this->tablicaInicjatywy[pierwszy->getInicjatywa()].erase(iter);
				break;
			}
		}
	}
	if (!this->tablicaInicjatywy[drugiInicjatywa].empty())
	{
		for (vector <Organizm*>::iterator iter = this->tablicaInicjatywy[drugiInicjatywa].begin(); iter < this->tablicaInicjatywy[drugiInicjatywa].end(); iter++)
		{
			if ((*iter)->getWiek() >= drugiWiek)
			{
				this->tablicaInicjatywy[drugiInicjatywa].insert(iter, pierwszy);
				break;
			}
			else if (iter == this->tablicaInicjatywy[drugiInicjatywa].end() - 1)
			{
				this->tablicaInicjatywy[drugiInicjatywa].push_back(pierwszy);
			}
		}
	}
	else
	{
		this->tablicaInicjatywy[drugiInicjatywa].push_back(pierwszy);
	}
	return;
}

Organizm* Swiat::nowyOrganizm(int x, int y, char znak)
{
	int pozycja = x * (this->rozmiarSwiata.y) + y;
	if (this->Organizmy[pozycja] != NULL)
	{
		return NULL;
	}
	else
	{
		Organizm* ptr = NULL;
		switch (znak)
		{
		case 'A':
		{
			ptr = new Antylopa(*this, x, y);
			break;
		}
		case 'j':
		{
			ptr = new WilczeJagody(*this, x, y);
			break;
		}
		case 'b':
		{
			ptr = new Barszcz(*this, x, y);
			break;
		}
		case 'L':
		{
			ptr = new Lis(*this, x, y);
			break;
		}
		case 't':
		{
			ptr = new Trawa(*this, x, y);
			break;
		}
		case 'g':
		{
			ptr = new Guarana(*this, x, y);
			break;
		}
		case 'C':
		{
			ptr = new Czlowiek(*this, x, y);
			break;
		}
		case 'O':
		{
			ptr = new Owca(*this, x, y);
			break;
		}
		case 'm':
		{
			ptr = new Mlecz(*this, x, y);
			break;
		}
		case 'Z':
		{
			ptr = new Zolw(*this, x, y);
			break;
		}
		case 'W':
		{
			ptr = new Wilk(*this, x, y);
			break;
		}
		default:
		{
			throw "Unable to read znak";
		}
		}

		this->Organizmy[pozycja] = ptr;
		this->tablicaInicjatywy[ptr->getInicjatywa()].push_back(ptr);
		return ptr;
	}
};