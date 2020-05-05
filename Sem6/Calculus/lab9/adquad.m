function y=adquad(a, b, f, err)
  y1 = Simpson(a, b, f);
  y2 = Simpson(a, (a+b)/2, f) + Simpson((a+b)/2, b, f);
  
  if abs(y1 - y2) < 15 * err
    y = y2;
    return;
  else
    y = adquad(a, (a+b)/2, f, err/2) + adquad((a+b)/2, b,f, err/2);
  end
end

function y = Simpson(a, b, f)
  y = (b-a)/6*(f(a) + f(b) + 4*f(a + (b-a)/2));
end