#include "Mlecz.h"

Mlecz::Mlecz(Swiat& swiat, int x, int y) :
	Roslina(swiat, x, y)
{
	this->znak = MLECZ;
}

Mlecz::~Mlecz()
{
}

bool Mlecz::akcja()
{
	this->rozmnoz(this->polozenie.x, this->polozenie.y);
	this->rozmnoz(this->polozenie.x, this->polozenie.y);
	return true;
}