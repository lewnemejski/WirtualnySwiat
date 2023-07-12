#include "Zwierze.h"
#include <cstdlib>
Zwierze::Zwierze(Swiat& swiat, int x, int y) :
	Organizm(swiat, x, y)
{
}

void Zwierze::ruch(int pola)
{
	int dx, dy;
	do
	{
		dx = rand() % 3 - 1;
		dy = rand() % 3 - 1;
	} while (!swiat.wGranicach(this->polozenie.x + dx, this->polozenie.y + dy) || (dy == 0 && dx == 0));
	swiat.zarzadzanieRuchem(this->polozenie.x, this->polozenie.y, this->polozenie.x + dx, this->polozenie.y + dy);
}

Zwierze::~Zwierze()
{
}

void Zwierze::wykonajRuch()
{
	if (!this->akcja())return;
	this->ruch();
}

bool Zwierze::rozmnoz(int toX, int toY)
{
	bool done = 0;
	for (int x = -1; x < 2; x++)
	{
		for (int y = -1; y < 2; y++)
		{
			if (x == 0 && y == 0)continue;
			if (swiat.wGranicach(toX + x, toY + y) && swiat.pustePole(toX + x, toY + y))
			{
				swiat.nowyOrganizm(toX + x, toY + y, this->znak);
				return true;
			}
		}
	}
	return false;
}