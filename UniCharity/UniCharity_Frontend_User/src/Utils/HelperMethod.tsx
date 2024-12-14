export const processHtml = (html: string) => {
    return html.replace(/<img /g, '<img style="width: 100%;" class="img-fluid" ');
};