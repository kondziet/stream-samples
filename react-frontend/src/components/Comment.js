import { HiOutlineUser } from "react-icons/hi"

function Comment({ comment }) {

    return(
        <div className="bg-gray-200 rounded p-2">
            <div className="flex items-center gap-2">
                <div className="rounded-full bg-white p-1">
                    <HiOutlineUser size={20} />
                </div>
                <p className="font-semibold">{comment.authorNickname}</p>
            </div>
            {comment.content}
        </div>        
    );
}

export default Comment;