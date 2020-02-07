goTopButton = document.getElementById("goTopBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
    showGoTopButton()
};

// let lastSelectId = 1;

fetch("/api/hashFunctions")
    .then(hashFunctions => hashFunctions.json())
    .then(hashFunctions => addOptions(document.getElementById("hashFunction"), hashFunctions))
    .catch(e => console.log(e));

fetch("/api/extensions")
    .then(extensions => extensions.json())
    .then(extensions => addOptions(document.getElementById("extension#0"), extensions))
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
    let select = selectForm.getElementsByTagName("select")[0];
    const newSelect = select.cloneNode(true);
    const id = parseInt(newSelect.id.split("#")[1]) + 1;
    newSelect.hidden = false;
    newSelect.id = "extentionSelect#" + id;
    // newSelectForm.getElementsByClassName("removeBtn")[0].removeAttribute("disabled");
    select.parentElement.appendChild(newSelect);
    // lastSelectId++;
}

function removeExtension(selectForm) {
    let selectors = selectForm.getElementsByTagName("select");
    if ((selectors.length - 1) > 0) {
        let select = selectors[selectors.length - 1];
        select.parentNode.removeChild(select);
    }
    // const
}

function addOptions(select, options) {
    options.forEach(option => {
        let opt = document.createElement("option");
        opt.value = option;
        opt.textContent = option;
        select.appendChild(opt);
    })
}

function showResult(resultHash) {
    document.getElementById("result").value = resultHash;
    document.getElementById("resultDiv").hidden = false;
}

function calculateHash() {
    document.getElementById("resultDiv").hidden = true;

    let data = {};
    data["extensions"] = [];
    fillDataWithFields(data, "select");
    fillDataWithFields(data, "input");

    fetch("/calculate", {
        method: 'POST',
        mode: 'same-origin', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    })
        .then(resultHash => resultHash.text())
        .then(resultHash => showResult(resultHash));
}

function fillDataWithFields(data, tagName) {
    Array.prototype.forEach.call(document.getElementsByTagName(tagName),
        (select) => {
            if (select.type === "checkbox") {
                data[select.id] = select.checked;
            } else if (select.id.startsWith("extension")) {
                data["extensions"].push(select.value);
            } else {
                data[select.id] = select.value;
            }
        });
}