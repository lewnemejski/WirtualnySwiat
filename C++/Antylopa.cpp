#include "Antylopa.h"

Antylopa::Antylopa(Swiat& swiat, int x, int y) :
	Zwierze(swiat, x, y)
{
	this->sila = 4;
	this->inicjatywa = 4;
	this->znak = ANTYLOPA;
}

Antylopa::~Antylopa()
{
}

bool Antylopa::akcja()
{
	this->ruch(2);
	return false;
}

bool Antylopa::kolizja(Organizm* other)
{
	if (rand() == 0)
	{
		for (int x = -1; x < 2; x++)
		{
			for (int y = -1; y < 2; y++)
			{
				if (swiat.pustePole(x, y))
				{
					swiat.zamienMiejscami(this->polozenie.x, this->polozenie.y, this->polozenie.x + x, this->polozenie.y + y);
				}
			}
		}
		return false;
	}
	return true;
}