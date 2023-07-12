#include "Guarana.h"

Guarana::Guarana(Swiat& swiat, int x, int y) :
	Roslina(swiat, x, y)
{
	this->znak = GUARANA;
}

Guarana::~Guarana()
{
}

bool Guarana::kolizja(Organizm* other)
{
	other->setSila(other->getSila() + 3);
	return true;
}
