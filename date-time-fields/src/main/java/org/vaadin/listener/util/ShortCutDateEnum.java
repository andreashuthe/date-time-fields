package org.vaadin.listener.util;

import elemental.events.KeyboardEvent;

import java.util.*;

/**
 * Created by andreas_h on 04.06.17.
 */
public enum ShortCutDateEnum {
    DE_NOW("Heute", Locale.GERMAN, KeyboardEvent.KeyCode.H, DateTimeShortCutDateListenerUtil.NOW),
    DE_TOMORROW("Morgen", Locale.GERMAN, KeyboardEvent.KeyCode.M, DateTimeShortCutDateListenerUtil.TOMORROW),
    DE_YESTERDAY("Gestern", Locale.GERMAN, KeyboardEvent.KeyCode.G, DateTimeShortCutDateListenerUtil.YESTERDAY),
    EN_NOW("Now", Locale.ENGLISH, KeyboardEvent.KeyCode.N, DateTimeShortCutDateListenerUtil.NOW),
    EN_TOMORROW("Tomorrow", Locale.ENGLISH, KeyboardEvent.KeyCode.T, DateTimeShortCutDateListenerUtil.TOMORROW),
    EN_YESTERDAY("Yesterday", Locale.ENGLISH, KeyboardEvent.KeyCode.Y, DateTimeShortCutDateListenerUtil.YESTERDAY),
    NOW("Now", null, KeyboardEvent.KeyCode.N, DateTimeShortCutDateListenerUtil.NOW),
    TOMORROW("Tomorrow", null, KeyboardEvent.KeyCode.T, DateTimeShortCutDateListenerUtil.TOMORROW),
    YESTERDAY("Yesterday", null, KeyboardEvent.KeyCode.Y, DateTimeShortCutDateListenerUtil.YESTERDAY),
    PLUS("ADD ONE", null, KeyboardEvent.KeyCode.NUM_PLUS, DateTimeShortCutDateListenerUtil.PLUS),
    MINUS("ADD ONE", null, KeyboardEvent.KeyCode.NUM_MINUS, DateTimeShortCutDateListenerUtil.MINUS),
    PLUS_KEY("ADD ONE", null, KeyboardEvent.KeyCode.EQUALS, DateTimeShortCutDateListenerUtil.PLUS),
    MINUS_KEY("ADD ONE", null, KeyboardEvent.KeyCode.DASH, DateTimeShortCutDateListenerUtil.MINUS)
    ;


    private static final Map<Locale, List<ShortCutDateEnum>> lookupLocale = new HashMap<Locale, List<ShortCutDateEnum>>();
    private static final List<ShortCutDateEnum> lookupNoLocale = new ArrayList<ShortCutDateEnum>();

    static {
        //Create reverse lookup hash map
        for (final ShortCutDateEnum shortCutDateEnum : ShortCutDateEnum.values())
            if (shortCutDateEnum.getLocale() == null) {
                lookupNoLocale.add(shortCutDateEnum);
            } else {
                if (!lookupLocale.containsKey(shortCutDateEnum.getLocale())){
                    lookupLocale.put(shortCutDateEnum.getLocale(), new ArrayList<ShortCutDateEnum>());
                }
                lookupLocale.get(shortCutDateEnum.getLocale()).add(shortCutDateEnum);
            }
    }

    public static List<ShortCutDateEnum> getShortCutDateEnums(final Locale locale){
        return lookupLocale.get(locale);
    }

    public static List<ShortCutDateEnum> getShortCutDateEnums(){
        return lookupNoLocale;
    }


    private final String desc;
    private final Locale locale;
    private final int keyCode;
    private final int type;

    ShortCutDateEnum (final String desc, final Locale locale, final int keyCode , final int type){
        this.desc = desc;
        this.locale = locale;
        this.keyCode = keyCode;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public Locale getLocale() {
        return locale;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getType(){
        return type;
    }
}
