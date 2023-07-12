#pragma once
#include <vector>
#include "Dziennik.h"
#include "Defines.h"

class Czlowiek;
class Swiat
{
public:

	void start();

	Swiat(int x, int y);

	~Swiat();

	int tura;

	void rysujSwiat();

	void zarzadzanieRuchem(int fromX, int fromY, int toX, int toY);

	void usunOrganizm(Organizm&);

	void barszczSmierci(int ktory);

	void zamienMiejscami(int ad1X, int ad1Y, int ad2X, int adY);

	bool pustePole(int x, int y);

	bool wGranicach(int x, int y);

	int getX();

	int getY();

	void aktualizujTabliceInicjatywy(Organizm* pierwszy, int drugiInicjatywa, int drugiWiek);

	Organizm* nowyOrganizm(int x, int y, char znak);

	void zapisz();

	void wczytaj();

	void nowaTura();
private:

	Czlowiek* czlowiekWsk;

	Dziennik dziennik;

	void stworzSwiat();

	struct RozmiarSwiata
	{
		int x, y;
	} rozmiarSwiata;

	Organizm** Organizmy;

	vector <Organizm*> tablicaInicjatywy[MAX_INIT];
};