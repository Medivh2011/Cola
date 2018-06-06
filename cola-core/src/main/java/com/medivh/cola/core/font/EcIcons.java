package com.medivh.cola.core.font;

import com.joanzapata.iconify.Icon;

public enum  EcIcons  implements Icon{
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char mChar;

    EcIcons(char aChar) {
        mChar = aChar;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return mChar;
    }
}
