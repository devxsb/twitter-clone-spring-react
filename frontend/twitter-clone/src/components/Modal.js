import React from "react";
import {Close} from "@mui/icons-material";
import CommentBox from "./CommentBox";

export default function Modal({icon, setCount, id, profilePicture}) {
    const [showModal, setShowModal] = React.useState(false);
    return (
        <>
            <div onClick={() => setShowModal(true)}> {icon} </div>
            {showModal ? (
                <>
                    <div
                        className="flex justify-center items-center overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none">
                        <div className="relative w-1/2 my-6 mx-auto max-w-3xl">
                            <div
                                className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                                <div className="relative p-6 flex-auto" onSubmit={() => setShowModal(false)}>
                                    <Close className="mb-1" onClick={() => setShowModal(false)}/>
                                    <div className="flex space-x-4 px-4 py-3">
                                        <img
                                            src="https://pbs.twimg.com/profile_images/1617244452027879425/cODTtPoH_400x400.jpg"
                                            alt="Profile"
                                            className="w-11 h-11 rounded-full"/>
                                        <CommentBox tweetId={id} profilePicture={profilePicture}
                                                    setCount={setCount} closeModal={() => setShowModal(false)}/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="opacity-10 fixed inset-0 z-40 bg-black"/>
                </>
            ) : null}
        </>
    );
}