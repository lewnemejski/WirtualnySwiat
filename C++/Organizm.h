#pragma once
#include "Swiat.h"
#include <string>

using namespace std;

enum typOrganizmu {
	ANTYLOPA = 'A',
	WILCZEJAGODY = 'j',
	BARSZCZ = 'b',
	LIS = 'L',
	TRAWA = 't',
	GUARANA = 'g',
	CZLOWIEK = 'C',
	OWCA = 'O',
	MLECZ = 'm',
	ZOLW = 'Z',
	WILK = 'W'
};

class Organizm
{

public:
	Organizm(Swiat&, int x, int y);

	~Organizm();

	char getZnak();

	int getInicjatywa();

	void starzejSie();

	int getWiek();

	int getX();

	int getY();

	int getSila();

	bool getRozmnozylSie();

	void setX(int x);

	void setY(int y);

	void setSila(int sila);

	void setInicjatywaWiek(int inicjatywa, int wiek);

	void setRozmnozylSie(bool rozmnozylSie);

	string nazwaOrganizmu();

	virtual bool rozmnoz(int x, int y) = 0;

	virtual void wykonajRuch() = 0;

	virtual bool akcja()
	{
		return true;
	};
	virtual bool kolizja(Organizm* other)
	{
		return true;
	};
protected:

	Swiat& swiat;

	struct Polozenie
	{
		int x;
		int y;
	} polozenie;

	typOrganizmu znak;

	int sila;

	int inicjatywa;

	int wiek;

	bool rozmnozylSie = false;
};