package com.yefeng.message.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

public class WordFilter {
    private static final Logger log = LoggerFactory.getLogger(WordFilter.class);
    private static final String WORDS = "WORDS";
    private static final String REPLACE_CHAR = "**";
    private static HashMap sensitiveWordMap;
    private static int minMatchTYpe = 1;
    private static int maxMatchType = 2;

    public static String replaceWords(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        } else {
            List<String> words = Cache.get("WORDS");
            if (CollectionUtils.isEmpty(words)) {
                words = readWordsFile();
                Cache.put("WORDS", words);
            }

            return CollectionUtils.isEmpty(words) ? text : replaceSensitiveWord(words, text, minMatchTYpe);
        }
    }

    private static List<String> readWordsFile() {
        List<String> list = new ArrayList();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            Resource resource = (new DefaultResourceLoader()).getResource("classpath:word.txt");
            inputStream = resource.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String txt = "";

            while(StringUtils.isNotBlank(txt = bufferedReader.readLine())) {
                list.addAll(Arrays.asList(StringUtils.split(StringUtils.deleteWhitespace(StringUtils.replace(txt, "，", ",")), ",")));
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (Exception var14) {
            log.error("读取敏感词汇文件出错", var14);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception var13) {
                log.error("读取敏感词汇文件出错", var13);
            }

        }

        return list;
    }

    private static String replaceSensitiveWord(List<String> data, String txt, int matchType) {
        if (sensitiveWordMap == null) {
            addSensitiveWord(data);
        }

        String resultTxt = txt;
        List<String> set = getSensitiveWord(txt, matchType);

        for(Iterator iterator = set.iterator(); iterator.hasNext(); resultTxt = resultTxt.replaceAll((String)iterator.next(), "**")) {
        }

        return resultTxt;
    }

    private static void addSensitiveWord(List<String> datas) {
        sensitiveWordMap = new HashMap(datas.size());
        Iterator<String> iterator = datas.iterator();
        Map<String, Object> now = null;
        Object now2 = null;

        while(iterator.hasNext()) {
            now2 = sensitiveWordMap;
            String word = ((String)iterator.next()).trim();

            for(int i = 0; i < word.length(); ++i) {
                char key_word = word.charAt(i);
                Object obj = ((Map)now2).get(key_word);
                if (obj != null) {
                    now2 = (Map)obj;
                } else {
                    now = new HashMap();
                    now.put("isEnd", "0");
                    ((Map)now2).put(key_word, now);
                    now2 = now;
                }

                if (i == word.length() - 1) {
                    ((Map)now2).put("isEnd", "1");
                }
            }
        }

    }

    private static List<String> getSensitiveWord(String text, int matchType) {
        List<String> words = new ArrayList();
        Map now = sensitiveWordMap;
        int count = 0;
        int start = 0;

        for(int i = 0; i < text.length(); ++i) {
            char key = text.charAt(i);
            now = (Map)((Map)now).get(key);
            if (now != null) {
                ++count;
                if (count == 1) {
                    start = i;
                }

                if ("1".equals(((Map)now).get("isEnd"))) {
                    now = sensitiveWordMap;
                    words.add(text.substring(start, start + count));
                    count = 0;
                }
            } else {
                now = sensitiveWordMap;
                if (count == 1 && matchType == 1) {
                    count = 0;
                } else if (count == 1 && matchType == 2) {
                    words.add(text.substring(start, start + count));
                    count = 0;
                }
            }
        }

        return words;
    }

    public WordFilter() {
    }
}