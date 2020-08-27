package com.example.instaclone.Utils;

public class StringManipulation {

    public static String expandUsername(String username)
    {
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username)
    {
        return username.replace(" ", ".");
    }

    /**
     * Notes: Ex) In --> #tag1 #tag2 #othertag
     *            Out --> #tag1,#tag2,#othertag
     * @param caption
     * @return
     */
    public static String getTags(String caption)
    {
        if(caption.indexOf("#") > 0)
        {
            StringBuilder sb = new StringBuilder();
            char[] charArray = caption.toCharArray();
            boolean foundWord = false;

            for(char c : charArray)
            {
                if(c == '#')
                {
                    foundWord = true;
                    sb.append(c);
                }
                else
                {
                    if(foundWord)
                    {
                        sb.append(c);
                    }
                }
                if(c == ' ' )
                {
                    foundWord = false;
                }
            }
            String s = sb.toString().replace(" ", "").replace("#", ",#");
            return s.substring(1, s.length());
        }

        // Notes: If there's no tags, we return the same caption that was passed in
        return caption;
    }





}
