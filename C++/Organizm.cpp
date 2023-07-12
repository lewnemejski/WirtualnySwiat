#include "Organizm.h"


Organizm::Organizm(Swiat& worldRef, int x, int y) :
	swiat(worldRef)
{
	this->polozenie.x = x;
	this->polozenie.y = y;
	this->wiek = 0;
}

void Organizm::starzejSie()
{
	this->wiek++;
}

Organizm::~Organizm()
{
}

char Organizm::getZnak()
{
	return this->znak;
}

int Organizm::getInicjatywa()
{
	return inicjatywa;
}

int Organizm::getWiek()
{
	return wiek;
}

int Organizm::getX()
{
	return this->polozenie.x;
}

int Organizm::getY()
{
	return this->polozenie.y;
}

bool Organizm::getRozmnozylSie() {
	return this->rozmnozylSie;
}

void Organizm::setX(int x)
{
	this->polozenie.x = x;
}

void Organizm::setSila(int p)
{
	this->sila = p;
}

void Organizm::setY(int y)
{
	this->polozenie.y = y;
}

int Organizm::getSila()
{
	return this->sila;
}

void Organizm::setInicjatywaWiek(int inicjatywa, int wiek)
{
	swiat.aktualizujTabliceInicjatywy(this, inicjatywa, wiek);
	this->wiek = wiek;
	this->inicjatywa = inicjatywa;
	return;
}

void Organizm::setRozmnozylSie(bool rozmnozylSie) {
	this->rozmnozylSie = rozmnozylSie;
}

string Organizm::nazwaOrganizmu() {
	string wartosc;
	switch (this->znak)
	{
	case 'A':
	{
		wartosc += "ANTYLOPA ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'j':
	{
		wartosc += "WILCZEJAGODY ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'b':
	{
		wartosc += "BARSZCZ ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'L':
	{
		wartosc += "LIS ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 't':
	{
		wartosc += "TRAWA ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'g':
	{
		wartosc += "GUARANA ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'C':
	{
		wartosc += "CZLOWIEK ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'O':
	{
		wartosc += "OWCA ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'm':
	{
		wartosc += "MLECZ ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'Z':
	{
		wartosc += "ZOLW ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	case 'W':
	{
		wartosc += "WILK ("; wartosc += to_string(this->polozenie.x); wartosc += ","; wartosc += to_string(this->polozenie.y); wartosc += ")";
		break;
	}
	default:
	{
		return "Unable to read znak";
	}
	}
	return wartosc;
}