package com.github.chaossss.ishuhui.domain.model;

import java.util.ArrayList;

/**
 * Created by chaos on 2016/1/4.
 */
public class AllBookModels {
    public ReturnClazz Return;

    public class ReturnClazz
    {
        public ArrayList<AllBook> List;

        public class AllBook
        {
            public String Id;
            public String Title;
            public String FrontCover;
            public String RefreshTime;
            public String Explain;
            public String SerializedState;
            public String Author;
            public String LastChapterNo;
            public String ClassifyId;
            public Chapter LastChapter;

            public class Chapter
            {
                public String Id;
                public String Title;
                public String FrontCover;
                public String Sort;
                public String Images;
                public String RefreshTimeStr;
                public String ChapterNo;
                public String LastChapterNo;
                public String BookId;
            }
        }
    }
}
