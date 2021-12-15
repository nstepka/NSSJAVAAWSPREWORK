// steganography

function clearbits(colorval){
    
    var x = Math.floor(colorval/16)*16;
    return x;
}

function chop2hide(image){
    //for each pixel in the image
    for(var px of image.values()){
        //clear the low bits of the red
        px.setRed(clearbits(px.getRed()));   
        //clear the low bits of the green
        px.setGreen(clearbits(px.getGreen()));  
        //clear the low bits of the blue
        px.setBlue(clearbits(px.getBlue()));  
    }    
    //after doing each pixel return the image as the answer    
    return image;
    
}

function shift(image){
    //for each pixel in the image
    for(var px of image.values()){
        
    
        //shift the red bits over
        px.setRed(px.getRed() /16);
        //shift the green bits over
        px.setGreen(px.getGreen() /16);
        //shift the blue bits over
        px.setBlue(px.getBlue() /16);
    }
    return image;
    
}

function combine(show, hide){
    //make a new image the same size as the "show"(call it answer)
    var answer = new SimpleImage(show.getWidth(), show.getHeight());
    //for each pixel in answer
    for(var px of answer.values()){
        
    
        //get the x and y of that pixel
        var x = px.getX();
        var y = px.getY();
        //get the pixel in the same place from show
        var showPixel = show.getPixel(x,y);
        //get the pixel in the same place from hide
         var hidePixel = show.getPixel(x,y);
        //set the red of px to the sum of showPixel's red +hide Pixels red
        px.setRed(showPixel.getRed() + hidePixel.getRed());
        //set the green of px to the sum of showPixel's green +hide Pixels green
        px.setGreen(showPixel.getGreen() + hidePixel.getGreen());
        //set the blue of px to the sum of showPixel's blue +hide Pixels blue
        px.setBlue(showPixel.getBlue() + hidePixel.getBlue());
    }
    //after doing each pixel, return an answer of the image we called answer    
    return answer;
}



var start = new SimpleImage("usain.jpg");
var hide = new SimpleImage("skyline.jpg");

start = chop2hide(start);
hide = shift(hide);
var ans = combine(start,hide);
print(ans);
