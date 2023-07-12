#pragma once

#include <string>
#include <queue>

using namespace std;

class Organizm;

class Dziennik
{
public:
	Dziennik();
	~Dziennik();
	void aktualizujDziennik();
	void opisWalki(Organizm* zwyciezca, Organizm* przegrany);
	void opisRozmnazania(Organizm*);
	void czlowiekSuperMoc(int czas);
private:
	queue <string> dziennik;
	void dodajWpis(string tekst);
};
