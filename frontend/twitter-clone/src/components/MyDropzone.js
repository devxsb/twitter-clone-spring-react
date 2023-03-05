import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import AddAPhotoIcon from '@mui/icons-material/AddAPhoto';
import {useSelector} from "react-redux";
import UserService from "../service/UserService";

function MyDropzone() {
    const username = useSelector(state => state.reduxSlice.username)

    const onDrop = useCallback(acceptedFiles => {
        const formData = new FormData()
        formData.append("file", acceptedFiles[0])
        let userService = new UserService()
        userService.uploadUserProfileImage(formData, username)
            .then(() => console.log("file uploaded successfully"))
            .catch(err => console.log(err))
    }, [])

    const {getRootProps, getInputProps} = useDropzone({onDrop})

    return (
        <div {...getRootProps()} className="m-auto absolute opacity-80" style={{
            left: "50%",
            top: "50%",
            transform: "translateX(-50%) translateY(-50%)",
        }}>
            <input {...getInputProps()} />
            <AddAPhotoIcon style={{color: "white", cursor: "pointer"}} fontSize="small"/>
        </div>
    )
}

export default MyDropzone