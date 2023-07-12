#pragma once
#include "Zwierze.h"
class Lis :
	public Zwierze
{
public:
	Lis(Swiat& Swiat, int x, int y);
	~Lis();
	bool kolizja(Organizm*);
	bool akcja();
};