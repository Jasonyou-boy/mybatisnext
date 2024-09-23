package org.mybatisnext;

import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.SqlNode;

public class ExternalNode implements SqlNode {

    private final SqlNode contents;
    private final HandlerAdapter handlerAdapter;


    public ExternalNode(String name, String type, SqlNode contents){
        this.contents=contents;
       this.handlerAdapter=new HandlerAdapter(name,type);
    }

    @Override
    public boolean apply(DynamicContext context) {
        return handlerAdapter.apply(contents,context);
    }

}
