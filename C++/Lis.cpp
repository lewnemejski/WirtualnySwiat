#include "Lis.h"
#include "Defines.h"

Lis::Lis(Swiat& swiat, int x, int y) :
	Zwierze(swiat, x, y)
{
	this->sila = 3;
	this->inicjatywa = 7;
	this->znak = LIS;
}

Lis::~Lis()
{
}

bool Lis::akcja()
{
	return true;
}

bool Lis::kolizja(Organizm* other) //Tak nie do koñca jest to kolizja
{
	if (other->getSila() > this->sila) {
		int dx, dy;
		for (int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			dx = rand() % 3 - 1;
			dy = rand() % 3 - 1;
			if (swiat.pustePole(this->polozenie.x + dx, this->polozenie.y + dy))
			{
				swiat.zarzadzanieRuchem(this->polozenie.x, this->polozenie.y, this->polozenie.x + dx, this->polozenie.y + dy);
				return false;
			}
		}
	}
	return true;
}