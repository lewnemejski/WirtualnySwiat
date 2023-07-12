#include "Barszcz.h"

Barszcz::Barszcz(Swiat& swiat, int x, int y) :
	Roslina(swiat, x, y)
{
	this->znak = BARSZCZ;
	this->sila = 10;
}

Barszcz::~Barszcz()
{
}

bool Barszcz::kolizja(Organizm* other)
{
	swiat.usunOrganizm(*other);
	return false;
}

bool Barszcz::akcja()
{
	for (int dx = -1; dx < 2; dx++)
	{
		for (int dy = -1; dy < 2; dy++) {
			if (!swiat.pustePole(this->polozenie.x + dx, this->polozenie.y + dy) && swiat.wGranicach(this->polozenie.x + dx, this->polozenie.y + dy))
			{
				if (dx != 0 && dy != 0)
					swiat.barszczSmierci((this->polozenie.x + dx) * this->swiat.getY() + (dy + this->polozenie.y));
			}
		}
	}
	return true;
}