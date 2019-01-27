package ru.nemodev.runhero.entity.collision;


import ru.nemodev.runhero.entity.play.ContactType;

public interface Contactable
{
    void beginContact(Contactable contactable);

    void endContact(Contactable contactable);

    ContactType getContactType();
}
