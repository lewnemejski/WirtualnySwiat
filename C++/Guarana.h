#pragma once
#include "Roslina.h"
class Guarana :
	public Roslina
{
public:
	Guarana(Swiat& swiat, int x, int y);
	~Guarana();
private:
	bool kolizja(Organizm* other);
};