#pragma once
#include "Organizm.h"
class Roslina :
	public Organizm
{
public:
	Roslina(Swiat& swiat, int x, int y);

	~Roslina();

	void wykonajRuch();

	virtual bool rozmnoz(int x, int y);
};