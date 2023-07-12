#pragma once
#include "Roslina.h"
class Barszcz :
	public Roslina
{
public:
	Barszcz(Swiat& swiat, int x, int y);
	~Barszcz();
	bool kolizja(Organizm* other);
	bool akcja();
};