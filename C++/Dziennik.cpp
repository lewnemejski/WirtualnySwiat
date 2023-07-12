#include "Dziennik.h"
#include "Organizm.h"
#include <iostream>
#include "Defines.h"

Dziennik::Dziennik()
{
}

Dziennik::~Dziennik()
{
}

void Dziennik::dodajWpis(string tekst)
{
	this->dziennik.push(tekst);
}

void::Dziennik::aktualizujDziennik()
{
	int counter = 0;
	while (!dziennik.empty() && counter < MAX_LOGS)
	{
		cout << dziennik.front() << " ";
		this->dziennik.pop();
		counter++;
	}
	while (!dziennik.empty())dziennik.pop();
}

void Dziennik::opisWalki(Organizm* zwyciezca, Organizm* przegrany)
{
	string tekst = "\n";
	tekst += zwyciezca->nazwaOrganizmu();
	tekst += " zabija ";
	tekst += przegrany->nazwaOrganizmu();
	this->dodajWpis(tekst);
	return;
}

void Dziennik::opisRozmnazania(Organizm* org)
{
	string tekst = "\n";
	tekst += org->nazwaOrganizmu();
	tekst += " rozmnaza sie.";
	this->dodajWpis(tekst);
	return;
}

void Dziennik::czlowiekSuperMoc(int czas)
{
	string tekst = "\n";
	if (czas == 1)
		tekst += "Czlowiek uzyl super mocy!";
	else if (czas != 0) {
		tekst += "Czlowiek ma aktywna super moc! Pozostalo tur: ";
		tekst += 5 - czas + 48;
	}
	else tekst += "Czlowiek aktualnie nie korzysta z super mocy";
	this->dodajWpis(tekst);
}