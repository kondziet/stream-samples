import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import usePrivateClientRequest from "../hooks/usePrivateClientRequest";
import Post from "../components/Post";

function PostCreated() {

    const [createdPost, setCreatedPost] = useState([]);
    const privateClientRequest = usePrivateClientRequest();
    const { id } = useParams();

    useEffect(() => {
        let isMounted = true;
        const abortController = new AbortController();

        const cleanup = () => {
            isMounted = false;
            abortController.abort();
        };

        const getPosts = async () => {
            try {
                const response = await privateClientRequest.get(`/api/posts/${id}`, {
                    signal: abortController.signal
                });
                if (isMounted) {
                    setCreatedPost(response.data);
                }

            } catch (error) {
                console.error(error);
            }
        };

        getPosts();

        return cleanup;
    }, []);

    return(
        <div className="w-screen flex-grow max-w-[1240px] flex justify-center">
            <div className="mt-20">
                <h1 className="text-4xl font-bold text-green-600">
                    Share your stream wisdom,
                </h1>
                <h2 className="text-3xl font-semibold text-green-500">
                    inspire others with elegant code snippets!
                </h2>
                <Post post={createdPost} />
            </div>
        </div>
    );
}

export default PostCreated;