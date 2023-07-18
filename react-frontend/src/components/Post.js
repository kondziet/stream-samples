import SyntaxHighlighter from "react-syntax-highlighter/dist/esm/default-highlight";
import { atomOneDark } from "react-syntax-highlighter/dist/esm/styles/hljs";
import { HiOutlineUser } from "react-icons/hi"
import { FaRegHeart, FaHeart } from "react-icons/fa"
import { useState } from "react";
import usePrivateClientRequest from "../hooks/usePrivateClientRequest";
import useAuthenticationContext from "../hooks/useAuthentication";

function Post({ post }) {

    const [postLikesCount, setPostLikesCount] = useState(post.likesCount);
    const [postLiked, setPostLiked] = useState(false);

    const { userAuthenticated } = useAuthenticationContext();
    const privateClientRequest = usePrivateClientRequest();

    const handlePostLike = async () => {
        if (postLiked) {
            setPostLiked(liked => !liked);
            setPostLikesCount(count => count - 1);
            await privateClientRequest.delete(`/api/posts/${post.postId}/likes`)
        } else {
            setPostLiked(liked => !liked);
            setPostLikesCount(count => count + 1);
            await privateClientRequest.post(`/api/posts/${post.postId}/likes`)
        }
    };

    return(
        <div className="bg-stone-300 rounded max-w-[1000px] w-screen p-4 mt-6">
            <div className="flex justify-between">
                <div className="flex items-center justify-start gap-2">
                    <HiOutlineUser className="rounded bg-white" size={30} />
                    <p>{post.authorNickname}</p>
                </div>
                <div>
                    <h1>{post.postCategory}</h1>
                </div>
            </div>
            <h1>{post.title}</h1>
            <h1>{post.description}</h1>
            <SyntaxHighlighter language="Java" style={atomOneDark}>
                {post.code}
            </SyntaxHighlighter>
            <div className="flex items-center justify-start gap-2 mt-4">
                {postLiked ? (
                    <FaHeart onClick={handlePostLike} className="cursor-pointer" size={20} />
                ) : (
                    <FaRegHeart onClick={handlePostLike} className="cursor-pointer" size={20} />
                )}
                <h1>{postLikesCount ? postLikesCount : 0}</h1>
            </div>

        </div>
    );
}

export default Post;