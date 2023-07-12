#pragma once
#include "Roslina.h"
class WilczeJagody :
	public Roslina
{
public:
	WilczeJagody(Swiat& swiat, int x, int y);
	~WilczeJagody();
	bool kolizja(Organizm* other);
};