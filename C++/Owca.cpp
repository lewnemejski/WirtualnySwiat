#include "Owca.h"

Owca::Owca(Swiat& swiat, int x, int y) :
	Zwierze(swiat, x, y)
{
	this->sila= 4;
	this->inicjatywa= 4;
	this->znak = OWCA;
}

Owca::~Owca()
{
}