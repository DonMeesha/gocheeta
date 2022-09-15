const express = require('express');
const path = require('path');
const fs = require('fs');
const app = express();
const cors = require('cors');
const port = 8082;

var bodyparser = require('body-parser');
app.use(cors());

app.use(bodyparser.urlencoded({extended: true}));
app.use(bodyparser.json({ limit: '50mb' }))
/*
 * send the result as json contents
 */
app.use(bodyparser.json());
app.use(express.static(path.join(__dirname,'public')));

app.post("/image/save", (req, res) => {
    const base64String = req.body.baseString;
    const fileName = `${Date.now()}.png`;
    const path = `./public/${fileName}`;
    const base64Data = base64String.replace(/^data:([A-Za-z-+/]+);base64,/, '');
    fs.writeFileSync(path, base64Data,  {encoding: 'base64'});
    res.send({
        imagePath: fileName
    });
});

app.listen(port, () => {
  console.log(`File manager app listening on port ${port}`)
})