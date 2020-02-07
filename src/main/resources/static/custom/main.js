goTopButton = document.getElementById("goTopBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
    showGoTopButton()
};

// let lastSelectId = 1;

fetch("/api/hashFunctions")
    .then(hashFunctions => hashFunctions.json())
    .then(hashFunctions => {
        console.log(hashFunctions);
        addOptions(document.getElementById("hashFunction"), hashFunctions);
        return hashFunctions;
    })
    .catch(e => console.log(e));

fetch("/api/extensions")
    .then(extensions => extensions.json())
    .then(extensions => addOptions(document.getElementById("extensionSelect#1"), extensions))
    .catch(e => console.log(e));

function showGoTopButton() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        goTopButton.style.display = "block";
    } else {
        goTopButton.style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

function addExtension(selectForm) {
    const newSelectForm = selectForm.cloneNode(true);
    const id = parseInt(newSelectForm.getElementsByTagName("select")[0].id.split("#")[1]) + 1;
    newSelectForm.getElementsByTagName("select")[0].id = "extentionSelect#" + id;
    selectForm.parentElement.appendChild(newSelectForm);
    // lastSelectId++;
}

function removeExtension(selectForm) {
    selectForm.parentElement.removeChild(selectForm);
    // const
}

function addOptions(select, options) {
    console.log(options);
    options.forEach(option => {
        console.log(option);
        let opt = document.createElement("option");
        opt.value = option;
        opt.textContent = option;
        select.appendChild(opt);
    })
}