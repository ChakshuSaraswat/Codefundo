%code for norm of the array of images
D=dir('C:\Users\Chakshu\Documents\MATLAB');
sumMat=zeros(128,128);
diff=sumMat;
for i=3 :1: size(D,1)
    %D(i).name
    I=double(imread(D(i).name));
    if(i>=4) %reading secind time
        diff=(I-prevFrame);
        sumMat=sumMat+diff.*diff;
    end
    prevFrame=I;
    %imshow(I)
    

end
sumMat=sqrt(sumMat/(size(D,1)-4));
%imshow(sumMat/255);

%---------------------------------------
X=sumMat/255;
fname=('normout1.jpg');

%baseFileName = sprintf('Image #%d.png', k);
%fullFileName = fullfile(folder, baseFileName);
%imwrite(yourImage, fullFileName);

save(fname,'X')
fullFileName = fullfile('C:\Users\Chakshu\Desktop\normtest', fname);
%imwrite(X,fname);
imwrite(X,fullFileName);