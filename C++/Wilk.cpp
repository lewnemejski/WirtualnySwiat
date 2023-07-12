#include "Wilk.h"

Wilk::Wilk(Swiat& swiat, int x, int y) :
	Zwierze(swiat, x, y)
{
	this->sila = 9;
	this->inicjatywa = 5;
	this->znak = WILK;
}

Wilk::~Wilk()
{
}

bool Wilk::akcja()
{
	return true;
}

bool Wilk::kolizja(Organizm* other)
{
	return true;
}