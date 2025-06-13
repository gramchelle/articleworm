import Link from "next/link";
import { MessageCircle, ThumbsUp } from "lucide-react";

type Article = {
  // author: { name: string; image: string };
  name: string;
  id: number;
  title: string;
  content: string;
  // excerpt: string;
  coverImage?: string | null;
  // release: string;
  // likes: number;
  // comments: number;
};

export default function ProfileArticleCard({ data }: { data: Article }) {
  return (
    <div className="w-full flex flex-col items-center justify-center">
      <div className="w-full max-w-[680px] flex flex-col gap-3 mt-8 mx-6">
        <Link
          href={`/${data.name}/${data.id}`}
          className="w-full grid grid-cols-[5fr_2fr] grid-rows-[auto_auto]"
        >
          <div className="w-full flex flex-col justify-between gap-5">
            <div className="flex flex-col gap-2.5">
              <h2 className="font-extrabold text-xl md:text-2xl leading-[1.15] md:leading-[1.25] line-clamp-4 overflow-visible">
                {data.title}
              </h2>
              <div className="line-clamp-2 text-muted-foreground leading-5.5">
                {/* {data.content} */}Lorem, ipsum dolor sit amet consectetur
                adipisicing elit. Maxime ullam corrupti, repellat itaque
                consectetur maiores.
              </div>
            </div>

            <div className="hidden md:flex items-center gap-5 text-muted-foreground text-[13px]">
              {/* <p>{data.release}</p> */}
              10 days ago
              <div className="flex gap-1 items-center">
                <ThumbsUp size={15} />
                {/* <p>{data.likes}</p> */}
                379
              </div>
              <div className="flex gap-1 items-center">
                <MessageCircle size={15} />
                {/* <p>{data.comments}</p> */}
                21
              </div>
            </div>
          </div>
          <div className="w-full items-center flex pl-4 md:pl-10 justify-center">
            {data.coverImage ? (
              <img
                src={data.coverImage}
                alt={data.title}
                className="object-cover rounded-sm max-h-[120px] md:min-w-[120px]"
              />
            ) : (
              <div className=" rounded-sm w-[120px] h-[100px]" />
            )}
          </div>

          <div className="w-full md:hidden flex justify-between mt-4 col-span-2 md:col-span-1">
            <div className="flex items-center gap-5 text-muted-foreground text-[13px]">
              {/* <p>{data.release}</p> */}
              10 days ago
              <div className="flex gap-1 items-center">
                <ThumbsUp size={15} />
                {/* <p>{data.likes}</p> */}
                379
              </div>
              <div className="flex gap-1 items-center">
                <MessageCircle size={15} />
                {/* <p>{data.comments}</p> */}
                21
              </div>
            </div>
            <div className=""></div>
          </div>
        </Link>
      </div>
      <div className="w-full max-w-[680px] border-b mt-8" />
    </div>
  );
}
