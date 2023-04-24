const user = {
    id: 1,
    name: "Elnura",
    account: "Elnura Amanturova",
    email: "elnura.amantur@gmail.com",
    password: "",
    counterPublication: 0,
    counterFollower: 0,
    counterFollowing: 0,
    enabled: true,
    isAuthorised: true
};

const post = {
    id: 1,
    image: "https://media-cdn.tripadvisor.com/media/photo-s/1a/64/49/24/caption.jpg",
    description: "Cali sunset",
    userId: user.email,
    date: "22.11.2022",
    isLiked: true
};

const comment = {
    id: 1,
    text: "some text",
    publicationId: post.id,
    userId: user.email,
    date: "22.11.2022"
};


const posts = [];

function addPostToArray() {
    posts.push(post);
}


//#5 Работа с функциями.
function changeUserState(user) {
    user.isAuthorised = !user.isAuthorised;
    return user;
}

const test = changeUserState(user);
console.log(test);

function changePostState(post) {
    for (let i = 0; i < posts.length; i++) {
        if (post.id === posts[i].id) {
            if (post.isLiked) {
                post.isLiked = false;
            }
        }
    }
    return post;
}

function showSplashScreen() {
    const splashScreen = document.createElement('div');
    splashScreen.id = 'splash';
    splashScreen.innerHTML = '<button type="button" id="button" class="btn btn-info" style="font-size: 30px; border-color: black; border-radius: 5px; font-family: Book Antiqua">TAP HERE TO LOGIN</button>';
    splashScreen.setAttribute("style", "background-color: #D2B48C; width: 100%;" +
        "height: 400%; position: absolute;top:0;bottom:0;left:0;right:0;margin:auto; " +
        "font-family:courier new;font-size:75px;font-weight:inherit;display: flex; align-items: center; " +
        "justify-content: center; text-align: center");
    document.body.append(splashScreen);

    const splash = document.getElementById('button');
    splash.onclick = (event) => {
        splashScreen.remove();
    };
}

showSplashScreen();


function createCommentElement(comment) {
    const commentElement = document.createElement('div');
    commentElement.classList.add('comment');
    commentElement.style.borderStyle = 'solid';
    commentElement.style.borderColor = 'grey';

    const text = document.createElement('h2');
    text.textContent = comment.text;

    const date = document.createElement('p');
    date.textContent = comment.date;
    const userEmail = document.createElement('p');
    userEmail.textContent = comment.userId;

    commentElement.append(text);
    commentElement.append(date);
    commentElement.append(userEmail);
    document.body.append(commentElement);
    return commentElement;
}

function createPostElement(post) {
    const postElement = document.createElement('div');
    postElement.classList.add('post');
    postElement.id = 'postElement';
    postElement.style.marginRight = '40px';
    postElement.style.borderStyle = 'solid';
    postElement.style.borderColor = 'gray';
    postElement.style.paddingLeft = '20px';
    postElement.style.paddingRight = '20px';
    postElement.style.marginBottom = '20px';

    const divActionLike = document.createElement('div');
    divActionLike.classList.add('divActionLike');
    divActionLike.id = 'divActionLike';
    divActionLike.style.display = 'flex';

    const image = document.createElement('img');
    image.src = post.image;
    image.style.width = '500px';
    image.style.height = '500px';

    const description = document.createElement('h1');
    description.textContent = post.description;

    const date = document.createElement('h3');
    date.textContent = post.date;
    date.style.paddingLeft = '450px';
    date.style.paddingBottom = '20px';

    const userEmail = document.createElement('p');
    userEmail.textContent = post.userId;

    const footer = document.createElement('div_footer');
    footer.style.display = 'flex';
    footer.style.paddingTop = '20px';

    const like = document.createElement('div');
    like.classList.add('like');
    like.id = 'likeId';
    like.innerHTML = `<span class="h3 mx-2 muted">
          <i class="far fa-heart" style="color: dimgrey; font-size: 30px"></i>
        </span>`;

    addClickLike(like);

    const commentIcon = document.createElement('div');
    commentIcon.classList.add('commentIcon');
    commentIcon.id = 'commentIcon';
    commentIcon.style.marginLeft = '-80px';

    commentIcon.innerHTML = ` <a class="navbar-brand" href="#"><i class="bi bi-chat" id="chat"' +
        ' style="color: dimgrey; font-size: 30px"></i></a>`;

    const bookmark = document.createElement('div');
    bookmark.classList.add('bookmark');
    bookmark.id = 'bookmarkId';
    bookmark.style.marginLeft = '480px';
    bookmark.innerHTML = `<span class="h3 mx-2 muted">
          <i class="bi bi-bookmark" style="color: dimgrey; font-size: 30px"></i>
        </span>`;

    addBookmark(bookmark);


    const bigHeart = document.createElement('h1');
    bigHeart.classList.add('big-heart');
    bigHeart.id = 'heartId';
    bigHeart.innerHTML = `<span class="h3 mx-2">
          <i class="bi bi-heart-fill" style="color: dimgrey; font-size: 100px; opacity: 0%"></i>
        </span>`;
    bigHeart.style.position = 'absolute';
    bigHeart.style.paddingLeft = '220px';
    bigHeart.style.paddingTop = '130px';
    doubleClickLike(bigHeart);

    const commentsBlock = document.createElement('div');
    commentsBlock.id = 'commentsBlock';
    commentsBlock.style.paddingBlock = '15px';

    footer.append(like);
    footer.append(bookmark);
    footer.append(commentIcon);
    divActionLike.append(image);
    divActionLike.append(bigHeart);
    postElement.append(divActionLike);
    postElement.append(footer);
    postElement.append(description);
    postElement.prepend(date);
    postElement.append(userEmail);
    postElement.append(commentsBlock);
    document.body.append(postElement);
    return postElement;
}

function createMainDiv(child) {
    const allPosts = document.createElement('div');
    allPosts.classList.add('all-posts');
    allPosts.id = 'all-posts';
    allPosts.style.display = 'inline-block';
    allPosts.append(child);
    document.body.append(allPosts);
}


navbar();
createPostForm();
createRegisterForm();
createMainDiv(createPostElement(post));
createCommentForm();

function addPost(postElement) {
    const posts = document.getElementById('all-posts');
    posts.append(postElement);
}

function addClickLike(like) {
    // const posts = document.querySelectorAll('all-posts');
    // posts.forEach(post => {
    //     const likeButton = document
    // })
    like.addEventListener('click', function () {
        const like = document.querySelector('.like');
        if (like.getAttribute('like') === null) {
            like.setAttribute('like', 'like');
            like.innerHTML = `<span class="h3 mx-2 text-danger">
              <i class="fas fa-heart" style="color: #FA8072; font-size: 30px"></i>
            </span>`;
        } else {
            like.removeAttribute('like');
            like.innerHTML = `<span class="h3 mx-2 muted">
          <i class="far fa-heart" style="color: dimgrey; font-size: 30px"></i>
        </span>`;
        }
    });
}


//#TASK2
function doubleClickLike(tap) {
    tap.addEventListener('dblclick', function () {
        const tap = document.getElementById('heartId');
        if (tap.getAttribute('big-heart') === null) {
            tap.setAttribute('big-heart', 'big-heart');
            tap.innerHTML = `<span class="h3 mx-5 text-danger">
          <i class="bi bi-heart-fill" style="color: white; font-size: 100px; opacity: 100%"></i>
        </span>`;
            setTimeout(function () {
                tap.removeAttribute('big-heart');
                tap.innerHTML = `<span class="h3 mx-5 text-danger">
          <i class="bi bi-heart-fill" style="color: indianred; font-size: 100px; opacity: 0%"></i>
        </span>`;
            }, 1000);
        }

        const like = document.getElementById('likeId');
        if (like.getAttribute('like') === null) {
            like.setAttribute('like', 'like');
            like.innerHTML = `<span class="h3 mx-2 text-danger">
              <i class="fas fa-heart" style="color: #FA8072; font-size: 30px"></i>
            </span>`;
        } else {
            like.removeAttribute('like');
            like.innerHTML = `<span class="h3 mx-2 muted">
          <i class="far fa-heart" style="color: dimgrey; font-size: 30px"></i>
        </span>`;
        }

    });
}

//#TASK3
function addBookmark(bookmark) {
    bookmark.addEventListener('click', function () {
        const bookmark = document.getElementById('bookmarkId');
        if (bookmark.getAttribute('bookmark') === null) {
            bookmark.setAttribute('bookmark', 'bookmark');
            bookmark.innerHTML = `<span class="h3 mx-5 text-danger">
          <i class="bi bi-bookmark-fill" style="color: #9ACD32; font-size: 30px"></i>
        </span>`;
        } else {
            bookmark.removeAttribute('bookmark');
            bookmark.innerHTML = `<span class="h3 mx-5 muted">
          <i class="bi bi-bookmark-fill" style="color: dimgrey; font-size: 30px"></i>
        </span>`;
        }
    });
}

function navbar() {
    const navbar = document.createElement('nav');
    navbar.classList.add('navbar');

    const plus = document.createElement('button');
    plus.innerHTML = ` <a class="navbar-brand" href="#"><i class="bi bi-plus-square" id="plus"></i></a>`;
    plus.style.borderColor = 'white';

    const register = document.createElement('button');
    register.innerHTML = `<a class="navbar-brand" href="#"><i class="bi bi-person-plus" id="registerButton"></i></a>`;
    register.style.borderColor = 'white';
    navbar.append(plus);
    navbar.append(register);
    document.body.append(navbar);

}

function createCommentForm() {
    const formComment = document.createElement('form');
    formComment.id = 'comment-form';
    formComment.style.borderColor = 'red';

    const inputTextComment = document.createElement('textarea');
    inputTextComment.name = 'comment';
    inputTextComment.placeholder = 'comment..';
    inputTextComment.style.paddingInline = '30px';

    const commentButton = document.createElement('button');
    commentButton.id = 'comment_button';
    commentButton.type = 'submit';
    commentButton.textContent = 'отправить';

    const inputUserId = document.createElement('input');
    inputUserId.type = 'hidden';

    const inputPostId = document.createElement('input');
    inputPostId.type = 'hidden';

    commentButton.style.display = 'block';
    commentButton.style.color = 'grey';
    commentButton.style.marginBlock = '10px';
    commentButton.style.marginInline = '5px';

    formComment.append(inputTextComment);
    formComment.append(inputUserId);
    formComment.append(inputPostId);
    formComment.append(commentButton);
    document.body.append(formComment);
}


function createPostForm() {
    const form = document.createElement('form');
    form.id = 'post-form';
    form.style.borderStyle = 'solid';
    form.style.borderColor = 'red';

    const inputImage = document.createElement('input');
    inputImage.type = 'file';
    inputImage.name = 'image';
    inputImage.style.display = 'block';
    inputImage.style.paddingBlock = '10px';
    inputImage.style.paddingInline = '5px';

    const inputDescription = document.createElement('input');
    inputDescription.type = 'text';
    inputDescription.name = 'description';
    inputDescription.style.display = 'block';
    inputDescription.placeholder = 'описание поста';
    inputDescription.style.marginBlock = '10px';
    inputDescription.style.marginInline = '5px';

    const inputIdUser = document.createElement('input');
    inputIdUser.type = 'hidden';


    const buttonSubmit = document.createElement('button');
    buttonSubmit.id = 'button_submit';
    buttonSubmit.type = 'submit';
    buttonSubmit.style.display = 'block';
    buttonSubmit.textContent = 'отправить';
    buttonSubmit.style.marginBlock = '10px';
    buttonSubmit.style.marginInline = '5px';

    form.append(inputImage);
    form.append(inputDescription);
    form.append(inputIdUser);
    form.append(buttonSubmit);
    document.body.append(form);
}


const postPlusIcon = document.getElementById('plus');
const postForm = document.getElementById('post-form');
postForm.style.display = 'none';

const commentIcon = document.getElementById('commentIcon');
const commentForm = document.getElementById('comment-form');
commentForm.style.display = 'none';


postPlusIcon.addEventListener('click', function () {
    if (postForm.style.display === 'none') {
        postForm.style.display = 'block';
    } else {
        postForm.style.display = 'none';
    }
});

commentIcon.addEventListener('click', function () {
    if (commentForm.style.display === 'none') {
        commentForm.style.display = 'block';
    } else {
        commentForm.style.display = 'none';
    }
})

document.getElementById('button_submit')
    .addEventListener('click', ev => {
        alert('do you wanna add post?');
    });


document.getElementById('comment_button')
    .addEventListener('click', ev => {
        alert('do you wanna comment?');
    });


async function postHandler(e) {
    e.preventDefault();
    const form = e.target;
    const data = new FormData(form);
    console.log(data);
    const newPost = {
        id: '',
        image: '/static/images/' + data.get('image').name,
        description: data.get('description'),
        userId: 'someuser@gmail.com',
        date: setTime(),
        isLiked: ''
    }
    console.log(newPost);
    addPost(createPostElement(newPost));

    await fetch('http://localhost:8090/publications/', {
        mode: 'no-cors',
        method: 'POST',
        body: data
    });

}

function setTime() {
    const date = new Date();
    const n = date.toDateString();
    const time = date.toLocaleTimeString();
    console.log('Date: ' + n);
    console.log('Time: ' + time);
    return date;
}

async function commentHandler(e) {
    e.preventDefault();
    const data = new FormData(e.target);
    console.log(prepareJson(data));

    const newComment = {
        id: 1,
        text: data.get('comment'),
        publicationId: 1,
        userId: user.email,
        date: setTime()
    }
    console.log(newComment);
    const comment = document.getElementById('commentsBlock');
    comment.append(createCommentElement(newComment));

    await fetch('http://localhost:8090/comments/', {
        mode: 'no-cors',
        method: 'POST',
        body: data
    })
}

postForm.addEventListener('submit', postHandler);
commentForm.addEventListener('submit', commentHandler);

function prepareJson(formData) {
    const object = {};
    formData.forEach(function (value, key) {
        object[key] = value;
    });
    return JSON.stringify(object);
}


const allForm = document.createElement('form');
const allPosts = document.createElement('button');
allPosts.id = 'button_submit';
allPosts.type = 'submit';
allPosts.textContent = 'all posts'
allPosts.style.display = 'block';
allPosts.textContent = 'показать все посты';
allPosts.style.marginBlock = '10px';
allPosts.style.marginInline = '5px';

allForm.append(allPosts);
document.body.append(allForm);

async function getPosts(e) {
    await fetch('http://localhost:8090/publications/allPosts/')
        .then(response => response.json())
        .then(posts => {
            posts.forEach(post => {
                createPostElement(post);
            })
        })
        .catch(error => console.error(error));
}

allForm.addEventListener('submit', getPosts);

const allFormComments = document.createElement('form');
const allCommentsShow = document.createElement('button');
allCommentsShow.id = 'button_submit';
allCommentsShow.type = 'submit';
allCommentsShow.textContent = 'all posts'
allCommentsShow.style.display = 'block';
allCommentsShow.textContent = 'показать все комментарии';
allCommentsShow.style.marginBlock = '10px';
allCommentsShow.style.marginInline = '5px';

allFormComments.append(allCommentsShow);
document.body.append(allFormComments);

async function getComments(e) {
    await fetch('http://localhost:8090/comments/allComments/')
        .then(response => response.json())
        .then(comments => {
            comments.forEach(comment => {
                createCommentElement(comment);
                const newComment = document.getElementById('commentsBlock');
                newComment.append(comment);
            })
        })
        .catch(error => console.error(error));
}

allFormComments.addEventListener('submit', getComments);

//registration hw-62
function createRegisterForm() {
    const registerForm = document.createElement('form');
    registerForm.id = 'register-form';
    registerForm.style.borderStyle = 'solid';
    registerForm.style.borderColor = 'green';
    registerForm.style.display = 'none';

    const name = document.createElement('input');
    name.type = 'text';
    name.name = 'name';
    name.placeholder = 'Введите имя';
    name.style.display = 'block';
    name.style.paddingBlock = '10px';
    name.style.paddingInline = '5px';

    const accountName = document.createElement('input');
    accountName.type = 'text';
    accountName.name = 'accountName';
    accountName.placeholder = 'Введите логин';
    accountName.style.display = 'block';
    accountName.style.paddingBlock = '10px';
    accountName.style.paddingInline = '5px';

    const email = document.createElement('input');
    email.type = 'text';
    email.name = 'email';
    email.placeholder = 'Введите почту';
    email.style.display = 'block';
    email.style.paddingBlock = '10px';
    email.style.paddingInline = '5px';

    const password = document.createElement('input');
    password.type = 'text';
    password.name = 'password';
    password.style.display = 'block';
    password.placeholder = 'Введите пароль';
    password.style.marginBlock = '10px';
    password.style.marginInline = '5px';

    const buttonRegister = document.createElement('button');
    buttonRegister.id = 'button-register';
    buttonRegister.type = 'submit';
    buttonRegister.textContent = 'отправить';
    buttonRegister.style.marginBlock = '10px';
    buttonRegister.style.marginInline = '5px';
    buttonRegister.style.display = 'block';

    registerForm.append(name);
    registerForm.append(accountName);
    registerForm.append(email);
    registerForm.append(password);
    registerForm.append(buttonRegister);
    document.body.append(registerForm);
}
const registerButton = document.getElementById('registerButton');
const registerForm = document.getElementById('register-form');

registerButton.addEventListener('click', function () {
    if (registerForm.style.display === 'none') {
        registerForm.style.display = 'block';
    } else {
        registerForm.style.display = 'none';
    }
});


async function onRegisterHandler(e) {
    e.preventDefault();
    const form = e.target;
    const data = new FormData(form);
    console.log(prepareJson(data));

    const name = data.get('name');
    const accountName = data.get('accountName');
    const email = data.get('email');
    const password = data.get('password');

    const user = {
        id: 1,
        name: name,
        account: accountName,
        email: email,
        password: password,
        counterPublication: 0,
        counterFollower: 0,
        counterFollowing: 0,
        enabled: true,
        isAuthorised: true
    }
    console.log(user);
    await fetch('http://localhost:8090/users/register/', {
        mode: 'no-cors',
        method: 'POST',
        cache: 'no-cache',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

const registrationForm = document.getElementById('register-form');
registrationForm.addEventListener('submit', onRegisterHandler);





