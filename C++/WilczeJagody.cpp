#include "WilczeJagody.h"

WilczeJagody::WilczeJagody(Swiat& swiat, int x, int y) :
	Roslina(swiat, x, y)
{
	this->znak = WILCZEJAGODY;
	this->sila = 99;
}

WilczeJagody::~WilczeJagody()
{
}

bool WilczeJagody::kolizja(Organizm* other)
{
	swiat.usunOrganizm(*other);
	swiat.usunOrganizm(*this);
	return false;
}
