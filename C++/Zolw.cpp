#include "Zolw.h"

Zolw::Zolw(Swiat& swiat, int x, int y) :
	Zwierze(swiat, x, y)
{
	this->sila = 2;
	this->inicjatywa = 1;
	this->znak = ZOLW;
}

Zolw::~Zolw()
{
}

bool Zolw::akcja()
{
	if (rand() % 4 == 0)return false;
	return true;
}

bool Zolw::kolizja(Organizm* org)
{
	if (org->getSila() < 5)return false;
	return true;
}