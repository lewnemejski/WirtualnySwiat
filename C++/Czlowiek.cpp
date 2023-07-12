#include "Czlowiek.h"
#include <conio.h>
#include "Defines.h"

Czlowiek::Czlowiek(Swiat& swiat, int x, int y) :
	Zwierze(swiat, x, y)
{
	this->znak = CZLOWIEK;
	this->inicjatywa = 4;
	this->sila = 5;
	this->superMocCounter = 0;
	this->superMocWaitCounter = 0;
}

int Czlowiek::superMoc()
{
	int pola = 0;
	if (this->superMocCounter > 0 && this->superMocCounter <= SUPER_POWER_TIME)
	{
		if (superMocCounter < 4)
			pola += 1;
		else {
			if (rand() % 2 == 0)
				pola += 1;
		}
		superMocCounter++;
	}
	if (this->superMocWaitCounter > 0)superMocWaitCounter--;
	if (this->superMocCounter > SUPER_POWER_TIME)
	{
		this->superMocCounter = 0;
		this->superMocWaitCounter = SUPER_POWER_TIME;
	}
	return pola;
}

void Czlowiek::ruch(int pola)
{
	pola += superMoc();
	//wlasciwy ruch
	int dx = 0, dy = 0;
	char ch = _getch();
	if (ch == 'z')		//zapis
	{
		if (!_kbhit())ch = _getch();
		swiat.zapisz();
		return;
	}
	if (ch == 'p')		//moc
	{
		if (!_kbhit())ch = _getch();
		if (this->superMocWaitCounter == 0) {
			this->superMocCounter++;
			pola += 1;
		}
	}
	if (_kbhit())ch = _getch();
	switch (ch)
	{
	case 72: //gora
	{
		dx = -1 * pola;
		break;
	}
	case 80:  //dol
	{
		dx = 1 * pola;
		break;
	}
	case 77:	//prawo
	{
		dy = 1 * pola;
		break;
	}
	case 75:	//lewo
	{
		dy = -1 * pola;
		break;
	}
	}
	if (swiat.wGranicach(this->polozenie.x + dx, this->polozenie.y + dy))
	{
		swiat.zarzadzanieRuchem(this->polozenie.x, this->polozenie.y, this->polozenie.x + dx, this->polozenie.y + dy);
	}
}

bool Czlowiek::rozmnoz(int x, int y)
{
	return false;
}

bool Czlowiek::kolizja(Organizm* other)
{
	return true;
}

Czlowiek::~Czlowiek()
{
}

void Czlowiek::setSuperMocCd(int liczba)
{
	this->superMocWaitCounter = liczba;
	return;
}

void Czlowiek::setSuperMocCount(int liczba)
{
	this->superMocCounter = liczba;
	return;
}

int Czlowiek::getSuperMocCd()
{
	return this->superMocWaitCounter;
}

int Czlowiek::getSuperMocCount()
{
	return this->superMocCounter;
}