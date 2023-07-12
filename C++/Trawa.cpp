#include "Trawa.h"

Trawa::Trawa(Swiat& swiat, int x, int y) :
	Roslina(swiat, x, y)
{
	this->znak = TRAWA;
}

Trawa::~Trawa()
{
}