package ch.ulrichfiedler.exp;

class SamplePagerItem {
    private final CharSequence mTitle;
    private final int mIndicatorColor;
    private final int mDividerColor;

    SamplePagerItem(CharSequence title, int indicatorColor,
                    int dividerColor) {
        mTitle = title;
        mIndicatorColor = indicatorColor;
        mDividerColor = dividerColor;
    }

    CharSequence getTitle() {
        return mTitle;
    }


    int getIndicatorColor() {
        return mIndicatorColor;
    }

    int getDividerColor() {
        return mDividerColor;
    }
}
