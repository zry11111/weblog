package com.zry.weblog.web.markdown;

import com.zry.weblog.web.markdown.provider.NofollowLinkAttributeProvider;
import com.zry.weblog.web.markdown.renderer.ImageNodeRenderer;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

public class MarkdownHelper {
    /**
     * Markdown 解析器
     */
    private final static Parser PARSER;
    /**
     * HTML 渲染器
     */
    private final static HtmlRenderer HTML_RENDERER;

    /**
     * 初始化
     */
    static {
        // Markdown 拓展
        List<Extension> extensions = Arrays.asList(
                TablesExtension.create(), // 表格拓展
                HeadingAnchorExtension.create(), // 标题锚点拓展
                ImageAttributesExtension.create(), // 图片宽高
                TaskListItemsExtension.create() // 任务列表
        );
        // 创建 Markdown 解析器和 HTML 渲染器，添加扩展
        PARSER = Parser.builder().extensions(extensions).build();
        HTML_RENDERER = HtmlRenderer
                .builder()
                .extensions(extensions)
                .attributeProviderFactory(context->new NofollowLinkAttributeProvider())
                .nodeRendererFactory(context -> new ImageNodeRenderer(context)) // 自定义图片渲染器
                .build();
    }

    /**
     * 将 Markdown 转换成 HTML
     */
    public static String convertMarkdown2Html(String markdown) {
        Node document = PARSER.parse(markdown);
        return HTML_RENDERER.render(document);
    }

    public static void main(String[] args) {
        String markdown = "This is *Sparta*";
        System.out.println(MarkdownHelper.convertMarkdown2Html(markdown));
    }
}
