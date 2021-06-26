const realFileBtn = document.getElementById("real-file");
const customBtn = document.getElementById("file-button");
const customTxt = document.getElementById("filetext");

customBtn.addEventListener("click", function () {
    realFileBtn.click();
});

realFileBtn.addEventListener("change", function () {
    if (realFileBtn.value) {
        customTxt.innerHTML = realFileBtn.value.match(/[\/\\]([\w\d\s\.\-\(\)]+)$/)[1];
    }
    else {
        customTxt.innerHTML = "No audio chosen, yet";
    }
});
