import axios from "axios";

export default class CommentService {
    sendComment(body) {
        return axios.post("/comments", body)
    }
}