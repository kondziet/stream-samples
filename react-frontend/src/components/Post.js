import SyntaxHighlighter from "react-syntax-highlighter/dist/esm/default-highlight";
import { atomOneDark } from "react-syntax-highlighter/dist/esm/styles/hljs";
import { HiOutlineUser } from "react-icons/hi"
import { FaRegHeart, FaHeart, FaRegComment } from "react-icons/fa"
import { AiOutlineSend } from "react-icons/ai"
import { useState } from "react";
import usePrivateClientRequest from "../hooks/usePrivateClientRequest";
import useAuthenticationContext from "../hooks/useAuthentication";
import Comment from "./Comment";
import clientRequest from "../api/clientRequest";

function Post({ post }) {

    const [postLikesCount, setPostLikesCount] = useState(post.likesCount);
    const [postLiked, setPostLiked] = useState(false);

    const [comments, setComments] = useState([]);
    const [commentCount, setCommentCount] = useState(post.commentsCount);
    const [commentSectionOpen, setCommentSectionOpen] = useState(false);
    const [commentInput, setCommentInput] = useState("");

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

    const togglePostComments = async () => {
        if (commentSectionOpen) {
            setCommentSectionOpen(open => !open);
        } else {
            await fetchComments();
            setCommentSectionOpen(open => !open);
        }
        
    };

    const fetchComments = async () => {
        try {
            const response = await clientRequest.get(`/api/comments/${post.postId}`);
            setComments(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const submitComment = async () => {
        try {
            const response = await privateClientRequest.post(
                `/api/comments/${post.postId}`, 
                {content: commentInput}
            );
            fetchComments();
            setCommentInput("");
        } catch (error) {
            console.error(error);
        }
    };

    const renderedComments = comments.map(comment => {
        return (
            <Comment key={comment.id} comment={comment} />
        );
    });

    return(
        <div className="bg-stone-300 rounded max-w-[1000px] w-screen p-4 mt-6">
            <div className="flex justify-between">
                <div className="flex items-center justify-start gap-3 bg-gray-500 rounded-l-2xl rounded-r-2xl p-2">
                    <div className="rounded-full bg-white p-1">
                        <HiOutlineUser size={30} />
                    </div>
                    <p className="font-semibold">{post.authorNickname}</p>
                </div>
                <div>
                    <p>{post.postCategory}</p>
                </div>
            </div>
            <div className="mt-2 text-xl font-bold">
                <p>{post.title}</p>
            </div>
            <div className="mt-2 p-2 bg-gray-400 rounded-t-lg">
                <p>{post.description}</p>
            </div>
            <SyntaxHighlighter language="Java" style={atomOneDark}>
                {post.code}
            </SyntaxHighlighter>
            <div className="flex items-center justify-start gap-4 mt-4">
                <div className="flex items-center justify-start gap-2 bg-orange-600 rounded-md p-2">
                    {postLiked ? (
                        <FaHeart onClick={handlePostLike} className="cursor-pointer" size={20} />
                    ) : (
                        <FaRegHeart onClick={handlePostLike} className="cursor-pointer" size={20} />
                    )}
                    <p>{postLikesCount ? postLikesCount : 0}</p>
                </div>
                <div className="flex items-center justify-start gap-2 bg-orange-600 rounded-md p-2">
                    <FaRegComment onClick={togglePostComments} className="cursor-pointer" size={20} />
                    <p>{commentCount ? commentCount : 0}</p>
                </div>
            </div>
            {commentSectionOpen ? (
                <div className="bg-gray-500 mt-4 rounded">
                    <div className="flex flex-col gap-2 p-2">
                        {comments.length === 0 ? (
                            <div className="flex items-center justify-center bg-gray-200 rounded p-2">
                                <p>Be the first to comment on this post!</p>
                            </div>
                        ) : (
                            <>{renderedComments}</>
                        )}
                    </div>
                    <div className="flex justify-center items-center gap-4 p-4">
                        <input value={commentInput} onChange={(event) => setCommentInput(event.target.value)} className="w-full px-1 py-1 border border-gray-500 rounded-md focus:outline-none focus:border-green-300" type="text"></input>
                        <button onClick={submitComment}><AiOutlineSend className="bg-white rounded" size={30} /></button>
                    </div>
                </div>
            ) : (<></>)}

        </div>
    );
}

export default Post;