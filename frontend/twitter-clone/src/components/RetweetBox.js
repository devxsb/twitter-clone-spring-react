import React, {useState} from "react";
import {useSelector} from "react-redux";
import CommentService from "../service/CommentService";
import Box from "./Box";

const RetweetBox = ({tweetId, setCount, closeModal}) => {
        const [content, setContent] = useState("");
        const currentUser = useSelector(state => state.reduxSlice.currentUser)

        const setContentFunc = x => setContent(x)

        const sendComment = () => {
            let body = {
                text: content,
                userId: currentUser,
                tweetId: tweetId
            }
            let commentService = new CommentService()
            commentService.sendComment(body).then(() => {
                setCount()
                closeModal()
            })
        };
        return (
            <Box content={content}
                 setContentFunc={setContentFunc}
                 sendFunc={sendComment}
                 submit="Tweet"
                 placeHolder="Add a comment"/>
        );
    }
;

export default RetweetBox;
