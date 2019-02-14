package ru.nemodev.runhero.entity.collision;


import ru.nemodev.runhero.entity.game.ContactType;

public interface Contactable
{
    void beginContact(Contactable contactable);

    void endContact(Contactable contactable);

    ContactType getContactType();
}
