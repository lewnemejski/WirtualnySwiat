#pragma once
#include "Organizm.h"
class Zwierze :
	public Organizm
{
public:
	Zwierze(Swiat&, int x, int y);
	~Zwierze();
	virtual bool rozmnoz(int toX, int toY);
protected:
	void wykonajRuch();
	virtual void ruch(int pola = 1);
};