#pragma once
#include "Zwierze.h"
class Czlowiek :
	public Zwierze
{
public:
	Czlowiek(Swiat& swiat, int x, int y);
	~Czlowiek();
	int superMoc();
	int getSuperMocCd();
	int getSuperMocCount();
	void setSuperMocCd(int);
	void setSuperMocCount(int);
	bool kolizja(Organizm*);
	bool rozmnoz(int, int);
private:
	int superMocCounter;
	int superMocWaitCounter;
	void ruch(int pola);
};