import React, {useEffect, useState} from "react";
import {ReplyIcon, ReTweetIcon, ShareIcon} from "../icons/Icon";
import profile from '../images/default-profile.png'
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import LikeService from "../service/LikeService";
import Modal from "./Modal";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";

const FeedItem = ({
                      text,
                      name,
                      image,
                      creationTimestamp,
                      username,
                      likes,
                      comments,
                      retweets,
                      id,
                      userProfileImageLink
                  }) => {
    const [likeCount, setLikeCount] = useState(likes && likes.length);
    const [commentsCount, setCommentsCount] = useState(comments && comments.length);
    const [retweetsCount, setRetweetsCount] = useState(retweets && retweets.length);
    const [isLiked, setIsLiked] = useState(false)
    const [likeId, setLikeId] = useState(undefined)

    const navigate = useNavigate()

    const currentUser = useSelector(state => state.reduxSlice.username)

    const likeClick = () => {
        let likeService = new LikeService()
        let body = {
            userId: localStorage.getItem("currentUser"),
            tweetId: id
        }
        if (isLiked) {
            likeService.deleteLike(likeId).then(() => {
                setIsLiked(false)
                setLikeId(undefined)
                setLikeCount(likeCount - 1)
            })
        } else {
            likeService.addLike(body).then(res => {
                setIsLiked(true)
                setLikeId(res.data.id)
                setLikeCount(likeCount + 1)
            })
        }
    }

    const checkLikes = () => {
        let likeControl = likes.find((like =>
            like.userId.toString() === localStorage.getItem("currentUser")));
        if (likeControl) {
            setIsLiked(true);
            setLikeId(likeControl.id)
        }
    }

    useEffect(() => {
        checkLikes()
    }, [isLiked])

    const setCommentCountFunc = () => setCommentsCount(commentsCount + 1)

    const setRetweetsCountFunc = () => setRetweetsCount(retweetsCount + 1)

    return (
        <>
            <article className="flex space-x-3 border-b border-gray-extraLight px-4 py-3 cursor-pointer">
                <img src={userProfileImageLink ? `http://localhost:8080/v1/users/${username}/image/download` : profile}
                     alt="Profile" className="w-11 h-11 rounded-full"
                     onClick={() => navigate("/" + username)}/>
                <div className="flex-1">
                    <div className="flex items-center text-sm">
                        <h4 className="font-bold">{name}</h4>
                        <span className="ml-2 text-gray-dark">@{username}</span>
                        <div className="mx-2 bg-gray-dark h-1 w-1 border rounded-full"/>
                        <span className="text-gray-dark">
                        {new Date(creationTimestamp).toLocaleString("tr-TR")}
                        </span>
                    </div>
                    <p className="mt-2 text-gray-900 text-sm" onClick={() => navigate("/tweets/" + id)}>{text}</p>
                    {image && <img src={image} className="my-2 rounded-xl max-h-96" alt={image}/>}
                    <ul className="-ml-1 mt-3 flex justify-between max-w-md">
                        <li className="flex items-center text-gray-dark text-sm group">
                            <Modal icon={
                                <div
                                    className="flex items-center justify-center w-8 h-8 rounded-full group-hover:bg-primary-light">
                                    <ReplyIcon className="w-5 h-5 group-hover:text-primary-base"/>
                                </div>} setCount={setCommentCountFunc}
                                   profilePicture={userProfileImageLink ? `http://localhost:8080/v1/users/${currentUser}/image/download` : profile}
                                   id={id}
                                   box="comment"/>
                            <span className="group-hover:text-primary-base">{commentsCount}</span>
                        </li>

                        <li className="flex items-center space-x-3 text-gray-dark text-sm group">
                            <Modal icon={
                                <div
                                    className="flex items-center justify-center w-8 h-8 rounded-full group-hover:bg-green-200 ">
                                    <ReTweetIcon className="w-5 h-5 group-hover:text-green-400"/>
                                </div>} setCount={setRetweetsCountFunc} profilePicture={userProfileImageLink ? `http://localhost:8080/v1/users/${currentUser}/image/download` : profile} id={id}
                                   box="retweet"/>
                            <span className="group-hover:text-primary-base">{retweetsCount}</span>
                        </li>

                        <li className="flex items-center  space-x-3 text-gray-dark text-sm group">
                            <div
                                className="flex items-center justify-center w-8 h-8 rounded-full group-hover:bg-pink-200"
                                onClick={likeClick}>
                                {isLiked ?
                                    <FavoriteIcon className="w-5 h-5 group-hover:text-gray-dark"
                                                  style={{color: "rgb(249, 24, 128)"}}/> :
                                    <FavoriteBorderIcon className="w-5 h-5 group-hover:text-gray-dark"/>
                                }
                            </div>
                            <span className="group-hover:text-pink-400">{likeCount}</span>
                        </li>

                        <li className="flex items-center  space-x-3 text-gray-dark text-sm group">
                            <div
                                className="flex items-center justify-center w-8 h-8 rounded-full group-hover:bg-primary-light ">
                                <ShareIcon className="w-5 h-5 group-hover:text-primary-base"/>
                            </div>
                        </li>
                    </ul>
                </div>
            </article>
        </>
    );
};

export default FeedItem;
