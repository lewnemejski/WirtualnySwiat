#include "Roslina.h"
#include "Defines.h"

Roslina::Roslina(Swiat& swiat, int x, int y) : Organizm(swiat, x, y)
{
	this->inicjatywa = 0;
	this->sila = 0;
}

Roslina::~Roslina()
{
}

void Roslina::wykonajRuch()
{
	this->akcja();
	this->rozmnoz(this->polozenie.x, this->polozenie.y);
}

bool Roslina::rozmnoz(int toX, int toY)
{
	if (rand() % PROBABILITY == 0)
	{
		for (int y = -1; y < 2; y++)
		{
			for (int x = -1; x < 2; x++)
			{
				if (swiat.wGranicach(x, y) && swiat.pustePole(toX + x, toY + y))
				{
					swiat.nowyOrganizm(toX + x, toY + y, this->znak);
					return true;
				}
			}
		}
	}
	return false;
}