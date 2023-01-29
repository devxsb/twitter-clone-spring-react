import React from 'react';
import {EmojiIcon, GIFIcon, ImageIcon, PollIcon, ScheduleIcon} from "../icons/Icon";

const Box = ({content, setContentFunc, sendFunc, submit, placeHolder}) => {
    return (
        <div className="flex flex-col flex-1 mt-2 px-2">
                  <textarea
                      className="w-full text-lg placeholder-gray-dark outline-none overflow-hidden resize-none bg-transparent"
                      placeholder={placeHolder}
                      onChange={(e) => setContentFunc(e.target.value)}
                      value={content}/>
            <div className="flex items-center justify-between">
                <div className="flex -ml-1">
                    <div className="flex items-center justify-center w-11 h-11 rounded-full hover:bg-gray-lightest">
                        <ImageIcon className="w-6 h-6 text-primary-base"/>
                    </div>
                    <div className="flex items-center justify-center w-11 h-11 rounded-full hover:bg-gray-lightest">
                        <GIFIcon className="w-6 h-6 text-primary-base"/>
                    </div>
                    <div className="flex items-center justify-center w-11 h-11 rounded-full hover:bg-gray-lightest">
                        <PollIcon className="w-6 h-6 text-primary-base"/>
                    </div>
                    <div className="flex items-center justify-center w-11 h-11 rounded-full hover:bg-gray-lightest">
                        <EmojiIcon className="w-6 h-6 text-primary-base"/>
                    </div>
                    <div className="flex items-center justify-center w-11 h-11 rounded-full hover:bg-gray-lightest">
                        <ScheduleIcon className="w-6 h-6 text-primary-base"/>
                    </div>
                </div>
                <button
                    className="bg-primary-base text-white rounded-full px-4 py-2 font-medium"
                    onClick={sendFunc}>
                    {submit}
                </button>
            </div>
        </div>
    );
}

export default Box;