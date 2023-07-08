import SyntaxHighlighter from "react-syntax-highlighter/dist/esm/default-highlight";
import { atomOneDark } from "react-syntax-highlighter/dist/esm/styles/hljs";

function Post({ post }) {

    return(
        <div>
            <h1>{post.title}</h1>
            <h1>{post.authorNickname}</h1>
            <SyntaxHighlighter language="Java" style={atomOneDark}>
                {post.code}
            </SyntaxHighlighter>
        </div>
    );
}

export default Post;